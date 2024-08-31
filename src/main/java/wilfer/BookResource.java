package wilfer;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;

@Path("books")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class BookResource {

    @Inject
    BookService bookService;

    @GET
    public Uni<Response> getAllBooks() {
        return bookService.findAll()
                .onItem().transform(books -> Response.ok(books).build());
    }

    @GET
    @Path("{id}")
    public Uni<Response> getBookById(@PathParam("id") Long id) {
        return bookService.findById(id)
                .onItem().transform(book -> book != null ?
                        Response.ok(book).build() :
                        Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Uni<Response> createBook(Book book) {
        return bookService.save(book).replaceWith(Response.ok(book).status(CREATED)::build);
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(Long id, Book book) {
        return bookService.updateBook(id, book)
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(Long id) {
        return bookService.delete(id)
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());
    }
}
