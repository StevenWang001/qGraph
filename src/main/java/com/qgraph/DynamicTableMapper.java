package com.qgraph;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicTableMapper {

  @Select("${sql}")
  Map<String, Object> query(@Param("sql") String querySql);

  @Select("${sql}")
  List<Map<String, Object>> batchQuery(@Param("sql") String querySql);

  @Select("${sql}")
  Long getLong(@Param("sql") String querySql);

  @Update("CREATE TABLE IF NOT EXISTS ${tableName} (${fields})")
  void createTable(@Param("tableName") String tableName, @Param("fields") String fields);

  @Insert("INSERT INTO ${targetTable}(${fields}) SELECT DISTINCT ${fields} FROM ${sourceTable}")
  void insertSelect(@Param("targetTable") String targetTable, @Param("sourceTable") String sourceTable, @Param("fields") String fields);

  @Insert("INSERT INTO ${tableName}(${fields}) VALUES(${values})")
  void insert(@Param("tableName") String tableName, @Param("fields") String fields, @Param("values") String values);

  @Insert("INSERT INTO ${tableName}(${fields}) VALUES ${values}")
  void batchInsert(@Param("tableName") String tableName, @Param("fields") String fields, @Param("values") String values);

  @Insert("${sql}")
  void insertOne(@Param("sql") String sql);

  @Delete("DELETE FROM ${tableName} WHERE __sys__timestamp__ < #{oldestRetentionTimeInMillis}")
  void deleteOutOfDateData(@Param("tableName") String tableName, @Param("oldestRetentionTimeInMillis") long oldestRetentionTimeInMillis);
}
