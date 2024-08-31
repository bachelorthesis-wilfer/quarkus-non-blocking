package wilfer;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    public Uni<List<Book>> findAll() {
        return bookRepository.listAll();
    }

    public Uni<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Uni<Book> save(Book book) {
        if (book == null || book.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        return Panache.withTransaction(book::persist);
    }

    public Uni<Book> updateBook(Long id, Book book) {
        return Panache
                .withTransaction(() -> bookRepository.findById(id)
                        .onItem().ifNotNull().invoke(entity -> {
                            entity.setAuthor(book.getAuthor());
                            entity.setTitle(book.getTitle());
                            entity.setPrice(book.getPrice());
                            entity.setIsbn(book.getIsbn());
                        })
                );
    }

    public Uni<Boolean> delete(Long id) {
        return Panache.withTransaction(() -> bookRepository.deleteById(id));
    }

}
