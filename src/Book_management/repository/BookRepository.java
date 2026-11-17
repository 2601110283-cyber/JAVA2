package Book_management.repository;

import Book_management.domain.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookRepository {
    ArrayList<Book> bookList;

    public ArrayList<Book> select(String searchWord, int selectedIndex){
        // ★ 오류 해결: 리스트 객체 생성(초기화) 추가
        bookList = new ArrayList<Book>();

        Connection con = JDBCConnector.getConnection();
        String[] columnName = {"name", "publish_name", "author"};
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select isbn, name, author, price, inventory, category_name, publish_name from book b, category c, publish p " +
                "where b.category_id = c.category_id and b.publish_id = p.publish_id " +
                "and " + columnName[selectedIndex] + " like ?";
        try {
            pstmt = con.prepareStatement(sql);
            // 검색어가 포함된 모든 데이터를 찾도록 앞뒤로 % 추가
            pstmt.setString(1, "%" + searchWord + "%");
            rs = pstmt.executeQuery();
            Book book = null;
            while (rs.next()){
                book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getInt("price"));
                book.setInventory(rs.getInt("inventory"));
                book.setCategory_name(rs.getString("category_name"));
                book.setPublish_name(rs.getString("publish_name"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return bookList;
    }
}