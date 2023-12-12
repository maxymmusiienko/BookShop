package com.example.bookshop.repository;

import com.example.bookshop.model.Book;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.persist(book);

            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error while adding a book", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Query<Book> query = session.createQuery("FROM Book", Book.class);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Error while getting all users", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Book> getById(Long id) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return Optional.ofNullable(session.get(Book.class, id));
        } catch (RuntimeException e) {
            throw new RuntimeException("Can`t get book from DB, ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
