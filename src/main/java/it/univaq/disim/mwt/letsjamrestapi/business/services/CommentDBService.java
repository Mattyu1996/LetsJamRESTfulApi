package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.SqlDb;
import it.univaq.disim.mwt.letsjamrestapi.models.Comment;
import it.univaq.disim.mwt.letsjamrestapi.models.CommentBody;

public class CommentDBService {

    public static Comment makeComment(ResultSet rs) {
        Comment c = new Comment();
        try {
            c.setContent(rs.getString("content"));
            c.setId(BigDecimal.valueOf(rs.getLong("id")));
            c.setUser(UserDBService.getUserById(BigDecimal.valueOf(rs.getLong("user_id"))).get(0));
            c.setMusicSheetId(BigDecimal.valueOf(rs.getLong("music_sheet_id")));
            c.setParentId(BigDecimal.valueOf(rs.getLong("parent_comment_id")));
            c.setReplies(BigDecimal.valueOf(rs.getLong("replies")));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return c;
    }

    public static List<Comment> getMusicsheetComments(BigDecimal musicsheetId) {
        Connection c = SqlDb.getConnection();
        try {
            List<Comment> commenti = new ArrayList<Comment>();
            PreparedStatement st = c.prepareStatement(
                    "SELECT *, (SELECT COUNT(b.id) FROM commenti AS b WHERE b.parent_comment_id = commenti.id) as replies FROM commenti WHERE music_sheet_id = ?");
            st.setLong(1, musicsheetId.longValue());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                commenti.add(makeComment(rs));
            }
            rs.close();
            return commenti;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Comment getCommentById(BigDecimal commentId) {
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement(
                    "SELECT *, (SELECT COUNT(b.id) FROM commenti AS b WHERE b.parent_comment_id = commenti.id) as replies FROM commenti WHERE id = ?");
            st.setLong(1, commentId.longValue());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return makeComment(rs);
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void addCommentToMusicsheet(BigDecimal musicsheetId, BigDecimal parentId, CommentBody body,
            BigDecimal userId) {
        Connection c = SqlDb.getConnection();
        System.out.println("dentro");
        System.out.println(body.getContent());
        System.out.println(musicsheetId);
        System.out.println(parentId);
        System.out.println(userId);
        try {
            PreparedStatement st = c
                    .prepareStatement("INSERT INTO commenti (content, music_sheet_id, user_id) VALUES (?,?,?)");
            st.setString(1, body.getContent());
            st.setLong(2, musicsheetId.longValue());
            st.setLong(3, userId.longValue());
            st.executeUpdate();
            c.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static List<Comment> getReplies(BigDecimal commentId) {
        Connection c = SqlDb.getConnection();
        try {
            List<Comment> commenti = new ArrayList<Comment>();
            PreparedStatement st = c.prepareStatement(
                    "SELECT *, (SELECT COUNT(b.id) FROM commenti AS b WHERE b.parent_comment_id = commenti.id) as replies FROM commenti WHERE parent_comment_id = ?");
            st.setLong(1, commentId.longValue());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                commenti.add(makeComment(rs));
            }
            rs.close();
            return commenti;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Comment updateComment(BigDecimal commentId, String content) {
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("UPDATE commenti SET content = ? WHERE id = ?");
            st.setString(1, content);
            st.setLong(2, commentId.longValue());
            st.executeUpdate();
            st.close();
            return getCommentById(commentId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
