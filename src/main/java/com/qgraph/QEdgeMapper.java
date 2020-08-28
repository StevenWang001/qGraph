package com.qgraph;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QEdgeMapper {

  @Insert("INSERT INTO edge(label, src_id, dest_id) VALUES(#{label}, #{srcId}, #{destId})")
  void insert(QEdge edge);

  @Select("SELECT * FROM edge WHERE id=#{id}")
  @Results(id = "resultMap", value = {
      @Result(property = "id", column = "id"),
      @Result(property = "label", column = "label"),
      @Result(property = "srcId", column = "src_id"),
      @Result(property = "destId", column = "dest_id"),
  })
  QEdge getById(Long id);

  @Select("SELECT * FROM edge WHERE src_id=#{id}")
  @ResultMap("resultMap")
  List<QEdge> getOutEdgesByVertexId(@Param("id") Long id);

  @Select({
      "<script>",
      "SELECT * FROM edge WHERE src_id=#{id} AND label IN ",
      "<foreach collection='labels' item='item' index='index' open='(' separator=',' close=')'>",
      "#{item}",
      "</foreach>",
      "</script>"
  })
  @ResultMap("resultMap")
  List<QEdge> getOutEdgesByVertexIdLabels(@Param("id") Long id, @Param("labels")String... labels);

  @Select("SELECT * FROM edge WHERE dest_id=#{id}")
  @ResultMap("resultMap")
  List<QEdge> getInEdgesByVertexId(@Param("id") Long id);

  @Select({
      "<script>",
      "SELECT * FROM edge WHERE dest_id=#{id} AND label IN ",
      "<foreach collection='labels' item='item' index='index' open='(' separator=',' close=')'>",
      "#{item}",
      "</foreach>",
      "</script>"
  })
  @ResultMap("resultMap")
  List<QEdge> getInEdgesByVertexIdLabels(@Param("id") Long id, @Param("labels")String... labels);

  @Select("SELECT * FROM edge WHERE src_id=#{id} OR dest_id=#{id}")
  @ResultMap("resultMap")
  List<QEdge> getBothEdgesByVertexId(@Param("id") Long id);

  @Select({
      "<script>",
      "SELECT * FROM edge WHERE (src_id=#{id} OR dest_id=#{id}) AND label IN ",
      "<foreach collection='labels' item='item' index='index' open='(' separator=',' close=')'>",
      "#{item}",
      "</foreach>",
      "</script>"
  })
  @ResultMap("resultMap")
  List<QEdge> getBothEdgesByVertexIdLabels(@Param("id") Long id, @Param("labels")String... labels);

  @Select("SELECT dest_id FROM edge WHERE src_id=#{id}")
  List<Long> getOutVertexIdsByVertexId(@Param("id") Long id);

  @Select("SELECT e2.dest_id FROM edge e2 JOIN (SELECT edge.dest_id as id FROM edge WHERE src_id=#{id}) AS e1 ON e2.src_id=e1.id")
  List<Long> get2OutVertexIdsByVertexId(@Param("id") Long id);
}
