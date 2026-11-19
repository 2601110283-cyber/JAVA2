package Book_management.view;

import Book_management.domain.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class BookInsertPan extends JPanel {
    JTable table;
    DefaultTableModel model;
    ArrayList<Book> bookArrayList;
    // 테이블 열 순서
    String[] tableHeader = {"도서번호", "도서명", "저자명", "출판사", "가격", "재고량", "카테고리"};
    // 입력 폼 순서 (텍스트필드 5개 → 콤보박스 2개)
    String[] header = {"도서번호", "도서명", "저자명", "가격", "재고량", "출판사", "카테고리"};
    String[] categoryNames = {"IT도서", "소설", "수필", "시"};
    String[] publishnames = {"한빛아카데미", "생능출판사", "민음사", "해냄출판사"};
    JLabel[] lbls = new JLabel[header.length];
    JTextField[] tfs = new JTextField[header.length - 2];
    JComboBox<String> comboCategory, comboPublish;
    JButton btnInsert = new JButton("도서추가");
    // 라벨+입력칸 2쌍씩 4줄, 가로간격 10 / 세로간격 5
    JPanel pans = new JPanel(new GridLayout(4, 4, 10, 5));

    public BookInsertPan() {
        setLayout(new BorderLayout());

        comboCategory = new JComboBox<String>(categoryNames);
        comboPublish = new JComboBox<String>(publishnames);

        for (int i = 0; i < header.length; i++) {
            lbls[i] = new JLabel(header[i], SwingConstants.RIGHT);  // 라벨 오른쪽 정렬
            pans.add(lbls[i]);
            if (i < header.length - 2) {
                tfs[i] = new JTextField();
                pans.add(tfs[i]);
            } else if (i == header.length - 2) {
                pans.add(comboPublish);   // 출판사
            } else {
                pans.add(comboCategory);  // 카테고리
            }
        }
        pans.add(btnInsert);

        // 폼 바깥 여백 (위, 왼쪽, 아래, 오른쪽)
        pans.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add("South", pans);
    }

    public void initView() {
        model = new DefaultTableModel(tableHeader, bookArrayList.size()) {
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

    // 테이블에 도서 목록 채우기
    public void putResult() {
        model.setRowCount(bookArrayList.size());
        Book book;
        for (int i = 0; i < bookArrayList.size(); i++) {
            book = bookArrayList.get(i);
            model.setValueAt(book.getIsbn(), i, 0);          // 도서번호
            model.setValueAt(book.getName(), i, 1);          // 도서명
            model.setValueAt(book.getAuthor(), i, 2);        // 저자명
            model.setValueAt(book.getPublish_name(), i, 3);  // 출판사
            model.setValueAt(book.getPrice(), i, 4);         // 가격
            model.setValueAt(book.getInventory(), i, 5);     // 재고량
            model.setValueAt(book.getCategory_name(), i, 6); // 카테고리
        }
    }

    public void setBookList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    public Book getBook() {
        Book book = new Book();
        book.setIsbn(tfs[0].getText().trim());
        book.setName(tfs[1].getText().trim());
        book.setAuthor(tfs[2].getText().trim());
        book.setPrice(Integer.parseInt(tfs[3].getText().trim()));
        book.setInventory(Integer.parseInt(tfs[4].getText().trim()));
        book.setPublish_name((String) comboPublish.getSelectedItem());
        book.setCategory_name((String) comboCategory.getSelectedItem());
        return book;
    }




    public JButton getBtnInsert() {
        return btnInsert;
    }


}