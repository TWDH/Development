package DesignKindle.enums;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public enum BookFormat {
    EPUB("epub"),
    PDF("pdf"),
    MOBI("mobi");

    private String content;

    BookFormat(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
