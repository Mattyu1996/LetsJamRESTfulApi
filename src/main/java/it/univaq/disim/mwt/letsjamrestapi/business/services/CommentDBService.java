package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.SqlDb;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.Comment;
import it.univaq.disim.mwt.letsjamrestapi.models.CommentBody;

public class CommentDBService {

    public static Comment makeComment(ResultSet rs) throws NotFoundException, SQLException {
        Comment c = new Comment();
        c.setContent(rs.getString("content"));
        c.setId(BigDecimal.valueOf(rs.getLong("id")));
        c.setUser(UserDBService.getUserById(BigDecimal.valueOf(rs.getLong("user_id"))));
        c.setMusicSheetId(BigDecimal.valueOf(rs.getLong("music_sheet_id")));
        c.setParentId(BigDecimal.valueOf(rs.getLong("parent_comment_id")));
        c.setReplies(BigDecimal.valueOf(rs.getLong("replies")));
        return c;
    }

    public static List<Comment> getMusicsheetComments(BigDecimal musicsheetId) throws NotFoundException, SQLException {
        Connection c = SqlDb.getConnection();
        List<Comment> commenti = new ArrayList<Comment>();
        PreparedStatement st = c.prepareStatement(
                "SELECT *, (SELECT COUNT(b.id) FROM commenti AS b WHERE b.parent_comment_id = commenti.id) as replies FROM commenti WHERE music_sheet_id = ?");
        st.setLong(1, musicsheetId.longValue());
        ResultSet rs = st.executeQuery();
        try {
            while (rs.next()) {
                commenti.add(makeComment(rs));
            }
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
        return commenti;
    }

    public static Comment getCommentById(BigDecimal commentId) throws NotFoundException, SQLException {
        Connection c = SqlDb.getConnection();
        PreparedStatement st = c.prepareStatement(
                "SELECT *, (SELECT COUNT(b.id) FROM commenti AS b WHERE b.parent_comment_id = commenti.id) as replies FROM commenti WHERE id = ?");
        st.setLong(1, commentId.longValue());
        ResultSet rs = st.executeQuery();
        try {
            if (rs.next()) {
                return makeComment(rs);
            }
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
        throw new NotFoundException("Comment not found");
    }

    public static void addCommentToMusicsheet(BigDecimal musicsheetId, BigDecimal parentId, CommentBody body,
            BigDecimal userId) throws SQLException {
        Connection c = SqlDb.getConnection();
        PreparedStatement st;
        if (parentId != null) {
            st = c.prepareStatement(
                    "INSERT INTO commenti (content, music_sheet_id, user_id, parent_comment_id) VALUES (?,?,?,?)");
            st.setLong(4, parentId.longValue());
        } else {
            st = c.prepareStatement("INSERT INTO commenti (content, music_sheet_id, user_id) VALUES (?,?,?)");
        }
        st.setString(1, body.getContent());
        st.setLong(2, musicsheetId.longValue());
        st.setLong(3, userId.longValue());
        try {
            st.executeUpdate();
        } finally {
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
    }

    public static List<Comment> getReplies(BigDecimal commentId) throws NotFoundException, SQLException {
        Connection c = SqlDb.getConnection();
        List<Comment> commenti = new ArrayList<Comment>();
        PreparedStatement st = c.prepareStatement(
                "SELECT *, (SELECT COUNT(b.id) FROM commenti AS b WHERE b.parent_comment_id = commenti.id) as replies FROM commenti WHERE parent_comment_id = ?");
        st.setLong(1, commentId.longValue());
        ResultSet rs = st.executeQuery();
        try {
            while (rs.next()) {
                commenti.add(makeComment(rs));
            }
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
        return commenti;
    }

    public static Comment updateComment(BigDecimal commentId, String content) throws NotFoundException, SQLException {
        Connection c = SqlDb.getConnection();
        PreparedStatement st = c.prepareStatement("UPDATE commenti SET content = ? WHERE id = ?");
        st.setString(1, content);
        st.setLong(2, commentId.longValue());
        try {
            st.executeUpdate();
        } finally {
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
        return getCommentById(commentId);
    }
}
