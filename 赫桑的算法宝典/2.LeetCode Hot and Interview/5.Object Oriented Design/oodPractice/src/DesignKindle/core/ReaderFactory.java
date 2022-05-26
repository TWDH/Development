package DesignKindle.core;

import DesignKindle.enums.BookFormat;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */

// Simple Factory
public class ReaderFactory {
    public IBookReader product(Book book) {
        if (book.getBookFormat() == BookFormat.EPUB) {
            return new EPUBReader();
        }
        else if (book.getBookFormat() == BookFormat.PDF) {
            return new PDFReader();
        }
        else if (book.getBookFormat() == BookFormat.MOBI) {
            return new MOBIReader();
        }
        return null;
    }
}
