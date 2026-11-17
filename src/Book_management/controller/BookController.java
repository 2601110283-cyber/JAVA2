package Book_management.controller;

import Book_management.domain.Book;
import Book_management.repository.BookRepository;
import Book_management.view.BookSearchPan;
import Book_management.view.center.SetFrame;

import javax.swing.*;
import java.util.ArrayList;

public class BookController extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    BookRepository repository;
    ArrayList<Book> bookList;
    JComboBox<String> combo;

    public BookController(){
        BookSearchPan searchPan = new BookSearchPan();
        combo = searchPan.getCombo();
        repository = new BookRepository();
        bookList = repository.select(searchPan.getSearchWard(), combo.getSelectedIndex());

        searchPan.setBookList(bookList);
        searchPan.initView();
        searchPan.putSearchResult(); // ★ 추가: 생성된 테이블에 book 데이터를 실제로 채워 넣음

        tabbedPane.add("도서 검색", searchPan);
        add(tabbedPane);

        SetFrame.setFrame(this, "도서관리 시스템", 700, 600);
    }

    public static void main(String[] args) {
        new BookController();
    }
}