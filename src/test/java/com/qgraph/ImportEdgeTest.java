package com.qgraph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class ImportEdgeTest {

  @Autowired
  DynamicTableMapper dynamicTableMapper;

  @Test
  public void insertVertex() {
//    List<Label> labels = dynamicMapper.getLabels();
//    Map<String, Label> map = new HashMap<>();
//    String labelName = "person";
//    labels.forEach(l -> map.put(l.getName(), l));

    String line;
    String filePath = "/Users/steven/Downloads/test-graph-edges.csv";

    try {
      BufferedReader bufferedReader =
          new BufferedReader(
              new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));

      int i = 0;
      StringBuilder edgeBuilder = new StringBuilder();

      while ((line = bufferedReader.readLine()) != null) {
        String[] items = line.split(",");

        edgeBuilder.append("(");
        edgeBuilder.append(items[0].replaceAll("-", ""));
        edgeBuilder.append(",'");
        edgeBuilder.append(items[1]);
        edgeBuilder.append("',");
        edgeBuilder.append(items[2].replaceAll("-", ""));
        edgeBuilder.append(")");
        edgeBuilder.append(", ");

        i++;

        if (i >= 1200) {
          edgeBuilder.delete(edgeBuilder.length() - 2, edgeBuilder.length());
          dynamicTableMapper.batchInsert("edge", "src_id, label, dest_id", edgeBuilder.toString());
          i = 0;
          edgeBuilder = new StringBuilder();
        }
      }

      if (i > 0) {
        edgeBuilder.delete(edgeBuilder.length() - 2, edgeBuilder.length());
        dynamicTableMapper.batchInsert("edge", "src_id, label, dest_id", edgeBuilder.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(1);
  }
}
