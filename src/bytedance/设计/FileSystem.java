package bytedance.设计;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.mkdir("/a/b/c");
        System.out.println(fileSystem.ls("/"));

    }
    private DirNode root;
    public FileSystem() {
        root = new DirNode("/", false);
    }

    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        String[] sections = getSections(path);
        DirNode cur = root;
        for (int i = 0; i < sections.length - 1; i++) {
            cur = (DirNode) cur.mapping.get(sections[i]);
        }
        Node node = sections.length == 0 ? root : cur.mapping.get(sections[sections.length - 1]);
        if (node.isFile) {
            res.add(node.name);
        } else {
            for (Node child : ((DirNode) node).children) {
                res.add(child.name);
            }
        }
        res.sort(String::compareTo);
        return res;
    }

    public void mkdir(String path) {
        String[] sections = getSections(path);
        DirNode cur = root;
        for (String section : sections) {
            if (!cur.mapping.containsKey(section)) {
                DirNode node = new DirNode(section, false);
                cur.children.add(node);
                cur.mapping.put(section, node);
            }
            cur = (DirNode) cur.mapping.get(section);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] sections = getSections(filePath);
        DirNode cur = root;
        for (int i = 0; i < sections.length - 1; i++) {
            cur = (DirNode) cur.mapping.get(sections[i]);
        }
        String fileName = sections[sections.length - 1];
        if (!cur.mapping.containsKey(fileName)) {
            FileNode node = new FileNode(fileName, true, content);
            cur.children.add(node);
            cur.mapping.put(fileName, node);
        } else {
            FileNode node = (FileNode) cur.mapping.get(fileName);
            node.content += content;
        }
    }

    public String readContentFromFile(String filePath) {
        String[] sections = getSections(filePath);
        DirNode cur = root;
        for (int i = 0; i < sections.length - 1; i++) {
            cur = (DirNode) cur.mapping.get(sections[i]);
        }
        String fileName = sections[sections.length - 1];
        return ((FileNode) cur.mapping.get(fileName)).content;
    }

    private String[] getSections(String path) {
        if (path.equals("/")) {
            return new String[0];
        }
        return path.substring(1).split("/");
    }

    static class Node {
        String name;
        boolean isFile;

        Node(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
        }
    }

    static class FileNode extends Node {
        String content;

        FileNode(String name, boolean isFile, String content) {
            super(name, isFile);
            this.content = content;
        }
    }

    static class DirNode extends Node {
        List<Node> children;
        Map<String, Node> mapping;
        DirNode(String name, boolean isFile) {
            super(name, isFile);
            this.children = new ArrayList<>();
            this.mapping = new HashMap<>();
        }
    }
}


