class BookFactory {
    createBook(type, title, author, ...extraParams) {
      switch (type) {
        case 'paper':
          return new PaperBook(title, author, extraParams[0]);  // Extra params: pages
        case 'ebook':
          return new EBook(title, author, extraParams[0]); // Extra params: file size in MB
        case 'audiobook':
          return new Audiobook(title, author, extraParams[0]); // Extra params: duration in hours
        default:
          throw new Error("Unknown book type!");
      }
    }
  }
  