package Book_management.view;

import Book_management.domain.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookSearchPan extends JPanel {
    JTextField fieldSearch = new JTextField(20);
    JButton btnSearch = new JButton("검색");
    String[] comboStr = {"도서명", "출판사명", "저자명"};
    JComboBox<String> combo = new JComboBox<String>(comboStr);
    JTable table;
    DefaultTableModel model;
    // ★ 수정: putSearchResult()의 열 순서(0~6번)와 정확히 일치하도록 헤더 순서 정렬
    // 1. 표 상단 제목 순서를 DB 테이블 컬럼 순서와 동일하게 설정
    String[] tableHeader = {"도서번호", "도서명", "저자명", "도서가격", "재고량", "카테고리", "출판사"};
    ArrayList<Book> bookList;
    String searchWard;

    public BookSearchPan(){
        setLayout(new BorderLayout());

        JPanel panN = new JPanel();
        JLabel lbl = new JLabel("검색어: ");
        panN.add(lbl);
        panN.add(fieldSearch);
        panN.add(combo);
        panN.add(btnSearch);

        add("North", panN);
    }

    public void initView(){
        model = new DefaultTableModel(tableHeader, bookList.size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        add("Center", scroll);
    }

    // 2. 각 열(0~6번)에 들어가는 데이터 순서도 제목과 똑같이 변경
    public void putSearchResult() {
        model.setRowCount(bookList.size());
        Book book = null;
        for (int i = 0; i < bookList.size(); i++) {
            book = bookList.get(i);
            model.setValueAt(book.getIsbn(), i, 0);          // 0: 도서번호
            model.setValueAt(book.getName(), i, 1);          // 1: 도서명
            model.setValueAt(book.getAuthor(), i, 2);        // 2: 저자명
            model.setValueAt(book.getPrice(), i, 3);         // 3: 도서가격
            model.setValueAt(book.getInventory(), i, 4);     // 4: 재고량
            model.setValueAt(book.getCategory_name(), i, 5); // 5: 카테고리
            model.setValueAt(book.getPublish_name(), i, 6);  // 6: 출판사
        }
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public JComboBox<String> getCombo() {
        return combo;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public String getSearchWard() {
        searchWard = fieldSearch.getText();
        return searchWard;
    }
}