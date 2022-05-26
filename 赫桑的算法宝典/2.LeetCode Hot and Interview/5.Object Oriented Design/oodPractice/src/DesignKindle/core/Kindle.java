package DesignKindle.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class Kindle {
    List<Book> bookList;
    ReaderFactory readerFactory;

    public Kindle() {
        this.bookList = new ArrayList<>();
        this.readerFactory = new ReaderFactory();
    }

    public String readBook(Book book) {
        IBookReader factory = readerFactory.product(book);
        String readContent = factory.read();
        System.out.println(readContent);

        return "Reading Finished !";
    }

    public void downloadBook(Book book) {
        bookList.add(book);
    }

    public void uploadBook(Book book) {
        bookList.add(book);
    }

    public void deleteBook(Book book) {
        bookList.remove(book);
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public ReaderFactory getReaderFactory() {
        return readerFactory;
    }

    public void setReaderFactory(ReaderFactory readerFactory) {
        this.readerFactory = readerFactory;
    }
}
