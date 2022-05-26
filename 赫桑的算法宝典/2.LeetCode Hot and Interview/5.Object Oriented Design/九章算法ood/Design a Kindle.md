![image-20220519151522374](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220519151522374.png)

![image-20220526100440331](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220526100440331.png)

```java
// K.Z
// Enum
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

// Interface
public interface IBookReader {
    public String read();
}

// Concrete Class
public class PDFReader implements IBookReader{
    @Override
    public String read() {
        return "This is the PDF Reader, Reading ...";
    }
}

public class MOBIReader implements IBookReader{
    @Override
    public String read() {
        return "This is MOBI Reader, Reading ...";
    }
}

public class EPUBReader implements IBookReader{
    @Override
    public String read() {
        return "This is EPUB Reader, Reading ...";
    }
}

// Factory
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

// Core
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
```



```java
// 九章算法
public class Kindle {
	private List<Book> books;
	private EBookReaderFactory readerFactory;

	public Kindle() {
		books = new ArrayList<>();
		readerFactory = new EBookReaderFactory();
	}

	public String readBook(Book book) throws Exception {
		EBookReader reader = readerFactory.createReader(book);
		if (reader == null) {
			throw new Exception("Can't read this format");
		}
		return reader.displayReaderType() + ", book content is: " + reader.readBook();
	}

	public void downloadBook(Book b) {
		books.add(b);
	}

	public void uploadBook(Book b) {
		books.add(b);
	}

	public void deleteBook(Book b) {
		books.remove(b);
	}
}

enum Format {
	EPUB("epub"), PDF("pdf"), MOBI("mobi");

	private String content;

	Format(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}

class Book {
	private Format format;

	public Book(Format format) {
		this.format = format;
	}

	public Format getFormat() {
		return format;
	}
}

abstract class EBookReader {
	
	protected Book book;
	
	public EBookReader(Book b){
		this.book = b;
	}
	
	public abstract String readBook();
	public abstract String displayReaderType();
}

class EBookReaderFactory {

	public EBookReader createReader(Book b) {
		if (b.getFormat() == Format.EPUB) {
			return new EpubReader(b);
		} else if (b.getFormat() == Format.MOBI) {
			return new MobiReader(b);
		} else if (b.getFormat() == Format.PDF) {
			return new PdfReader(b);
		} else
			return null;
	}
}

class EpubReader extends EBookReader{

	public EpubReader(Book b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String readBook() {
		// TODO Auto-generated method stub
		return book.getFormat().getContent();
	}

	@Override
	public String displayReaderType() {
		// TODO Auto-generated method stub
		return "Using EPUB reader";
	}
}

class MobiReader extends EBookReader {

	public MobiReader(Book b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String readBook() {
		// TODO Auto-generated method stub
		return book.getFormat().getContent();
	}

	@Override
	public String displayReaderType() {
		// TODO Auto-generated method stub
		return "Using MOBI reader";
	}

}
class PdfReader extends EBookReader{

	public PdfReader(Book b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String readBook() {
		// TODO Auto-generated method stub
		return book.getFormat().getContent();
	}

	@Override
	public String displayReaderType() {
		// TODO Auto-generated method stub
		return "Using PDF reader";
	}
}
```

