class Library {
    constructor() {
      if (Library.instance) {
        return Library.instance; 
      }
  
      this.books = []; 
      Library.instance = this; 
    }
  
    addBook(book) {
      this.books.push(book);
    }
  
    getBooks() {
      return this.books;
    }
  
    findBook(bookName) {
      return this.books.filter(book => book.title === bookName);
    }
  }
  
  const library1 = new Library();
  
  library1.addBook({ title: "JavaScript: The Good Parts", author: "Douglas Crockford" });
  library1.addBook({ title: "Clean Code", author: "Robert C. Martin" });
  
  console.log("Books in library1:", library1.getBooks());
  
  const library2 = new Library();
  
  library2.addBook({ title: "Design Patterns", author: "Erich Gamma" });
  
  console.log("Books in library2:", library2.getBooks());
  
  console.log("Are library1 and library2 the same instance?", library1 === library2);
  