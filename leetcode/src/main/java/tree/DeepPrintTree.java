package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeepPrintTree {
    static class Node {
        String name;
        int id;
        int parentId;
    }

    public static void main(String[] args) {
        List<Node> nodeList = List.of(
                new Node() {{
                    name = "1";
                    id = 1;
                    parentId = 0;
                }},
                new Node() {{
                    name = "2";
                    id = 2;
                    parentId = 1;
                }},
                new Node() {{
                    name = "3";
                    id = 3;
                    parentId = 1;
                }},
                new Node() {{
                    name ="4";
                }},
                new Node() {{
                    name = "5";
                    id = 5;
                    parentId = 3;
                }},
                new Node() {{
                    name = "6";
                    id = 6;
                    parentId = 5;
                }},
                new Node() {{
                    name = "7";
                    id = 7;
                    parentId = 5;
                }}
        );
        //打印这个nodeList为树形，每一层的子层都比上一层多两个空格的索引
        ArrayList<Node> nodes = new ArrayList<>(nodeList);
        Iterator<Node> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            System.out.println("node = " + node);
            iterator.remove();
        }
    }
}
