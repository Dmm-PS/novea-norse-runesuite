package io.ruin.services;

import io.ruin.Server;
import io.ruin.model.World;

import java.sql.ResultSet;
import java.sql.Statement;

public class LatestUpdate {

    public static String LATEST_UPDATE_URL;
    public static String LATEST_UPDATE_TITLE;

    public static void fetch() {
        Server.forumDb.execute(con -> {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT forums_topics.title, forums_topics.tid " +
                    "FROM forums_topics " +
                    "INNER JOIN forums_posts " +
                    "ON forums_topics.tid = forums_posts.topic_id AND forums_posts.new_topic = 0 " +
                    "WHERE forums_topics.forum_id = 2 ORDER BY forums_topics.start_date DESC LIMIT 1");
            while(resultSet.next()) {
                LATEST_UPDATE_URL = World.type.getWebsiteUrl() + "/forums/index.php?app=forums&module=forums&controller=topic&id=" + resultSet.getInt("tid");
                LATEST_UPDATE_TITLE = resultSet.getString("title");
            }

        });
    }
}
