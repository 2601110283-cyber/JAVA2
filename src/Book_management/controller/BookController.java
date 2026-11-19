package Book_management.controller;

import Book_management.domain.Book;
import Book_management.repository.BookRepository;
import Book_management.view.BookInsertPan;
import Book_management.view.BookSearchPan;
import Book_management.view.center.SetFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookController extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    BookRepository repository;
    ArrayList<Book> bookList;
    JComboBox<String> combo;
    BookSearchPan searchPan;
    BookInsertPan insertPan;

    public BookController() {
        repository = new BookRepository();

        // ===== 도서 검색 탭 =====
        searchPan = new BookSearchPan();
        combo = searchPan.getCombo();

        bookList = repository.select(searchPan.getSearchWard(), combo.getSelectedIndex());
        searchPan.setBookList(bookList);
        searchPan.initView();
        searchPan.putSearchResult();

        searchPan.getBtnSearch().addActionListener(btnSearchListener);
        searchPan.getFieldSearch().addActionListener(btnSearchListener);

        // ===== 도서 추가 탭 =====
        insertPan = new BookInsertPan();
        insertPan.setBookList(bookList);
        insertPan.initView();
        insertPan.putResult();

        JButton btnInsert = insertPan.getBtnInsert();
        btnInsert.addActionListener(btnInsertListener);   // ★ 도서추가 버튼에 리스너 등록

        tabbedPane.add("도서 검색", searchPan);
        tabbedPane.add("도서 추가", insertPan);
        tabbedPane.setSelectedIndex(1);   // 실행 시 '도서 추가' 탭이 먼저 보이게
        add(tabbedPane);

        SetFrame.setFrame(this, "도서관리 시스템", 700, 600);
    }

    ActionListener btnSearchListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            bookList = repository.select(searchPan.getSearchWard(), combo.getSelectedIndex());
            searchPan.setBookList(bookList);
            searchPan.putSearchResult();
        }
    };

    ActionListener btnInsertListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Book book;
            try {
                book = insertPan.getBook();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(insertPan, "가격과 재고량은 숫자로 입력하세요.");
                return;
            }

            // 빈 칸 확인
            if (book.getIsbn().isEmpty() || book.getName().isEmpty() || book.getAuthor().isEmpty()) {
                JOptionPane.showMessageDialog(insertPan, "도서번호, 도서명, 저자명을 모두 입력하세요.");
                return;
            }

            try {
                repository.insert(book);
            } catch (RuntimeException ex) {
                // 도서번호 중복 등 DB 오류
                JOptionPane.showMessageDialog(insertPan, "도서 추가 실패: 이미 있는 도서번호인지 확인하세요.");
                return;
            }

            // 추가 후 전체 목록을 다시 불러와 두 탭 모두 갱신
            ArrayList<Book> insertBookList = repository.select("", 0);
            insertPan.setBookList(insertBookList);
            insertPan.putResult();

            bookList = insertBookList;
            searchPan.setBookList(bookList);
            searchPan.putSearchResult();
        }
    };

    public static void main(String[] args) {
        new BookController();
    }
}