package com.qgraph;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QVertexMapper {

  @Insert("INSERT INTO vertex(id, label) VALUES(#{id}, #{label})")
  void insert(QVertex vertex);

  @Select("SELECT * FROM vertex WHERE id=#{id}")
  @Results(id = "resultMap", value = {
      @Result(property = "id", column = "id"),
      @Result(property = "label", column = "label"),
  })
  QVertex getById(Long id);

  @Select("SELECT * FROM vertex WHERE id IN (SELECT dest_id FROM edge WHERE src_id=#{id})")
  @ResultMap("resultMap")
  List<QVertex> getOutVertexIdsById(@Param("id") Long id);

  @Select("SELECT * FROM vertex WHERE id IN (SELECT src_id FROM edge WHERE dest_id=#{id})")
  @ResultMap("resultMap")
  List<QVertex> getInVertexIdsById(@Param("id") Long id);

  @Select("SELECT * FROM vertex WHERE id IN (SELECT dest_id FROM edge WHERE src_id=#{id} UNION SELECT src_id FROM edge WHERE dest_id=#{id})")
  @ResultMap("resultMap")
  List<QVertex> getBothVertexIdsById(@Param("id") Long id);

  @Select({
      "<script>",
      "SELECT * FROM vertex WHERE id IN ",
      "<foreach collection='ids' item='item' index='index' open='(' separator=',' close=')'>",
      "#{item}",
      "</foreach>",
      "</script>"
  })
  @ResultMap("resultMap")
  List<QVertex> findByIds(@Param("ids") List<Long> ids);
}
