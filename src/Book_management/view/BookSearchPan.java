package Book_management.view;

import Book_management.domain.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class BookSearchPan extends JPanel {
    JTextField fieldSearch = new JTextField(20);
    JButton btnSearch = new JButton("검색");
    String[] comboStr = {"도서명", "출판사명", "저자명"};
    JComboBox<String> combo = new JComboBox<String>(comboStr);
    JTable table;
    DefaultTableModel model;
    // 사진과 같은 열 순서
    String[] tableHeader = {"도서번호", "도서명", "저자명", "출판사", "가격", "재고량", "카테고리"};
    ArrayList<Book> bookList;
    String searchWard;

    public BookSearchPan() {
        setLayout(new BorderLayout());

        JPanel panN = new JPanel();
        JLabel lbl = new JLabel("검색어: ");
        panN.add(lbl);
        panN.add(fieldSearch);
        panN.add(combo);
        panN.add(btnSearch);

        add("North", panN);
    }

    public void initView() {
        model = new DefaultTableModel(tableHeader, bookList.size()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        TableColumnModel tcm = table.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(50);   // 도서번호
        tcm.getColumn(1).setPreferredWidth(200);  // 도서명
        tcm.getColumn(2).setPreferredWidth(130);  // 저자명
        tcm.getColumn(3).setPreferredWidth(100);  // 출판사
        tcm.getColumn(4).setPreferredWidth(50);   // 가격
        tcm.getColumn(5).setPreferredWidth(50);   // 재고량
        tcm.getColumn(6).setPreferredWidth(80);   // 카테고리

        JScrollPane scroll = new JScrollPane(table);
        add("Center", scroll);
    }

    public void putSearchResult() {
        model.setRowCount(bookList.size());
        Book book;
        for (int i = 0; i < bookList.size(); i++) {
            book = bookList.get(i);
            model.setValueAt(book.getIsbn(), i, 0);          // 도서번호
            model.setValueAt(book.getName(), i, 1);          // 도서명
            model.setValueAt(book.getAuthor(), i, 2);        // 저자명
            model.setValueAt(book.getPublish_name(), i, 3);  // 출판사
            model.setValueAt(book.getPrice(), i, 4);         // 가격
            model.setValueAt(book.getInventory(), i, 5);     // 재고량
            model.setValueAt(book.getCategory_name(), i, 6); // 카테고리
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

    public JTextField getFieldSearch() {
        return fieldSearch;
    }

    public String getSearchWard() {
        searchWard = fieldSearch.getText().trim();
        return searchWard;
    }
}