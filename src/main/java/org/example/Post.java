package org.example;


import java.util.HashSet;

class Post implements Comparable<Post> {
    private int id;
    private User user;
    private String content;
    private HashSet<Comment> comments;

    public Post(int id, User user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.comments = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public int compareTo(Post other) {
        return Integer.compare(this.id, other.id);
    }

    public void showComments() {
        for (Comment comment : comments) {
            System.out.println(comment.getUser().getName() + ": " + comment.getContent());
        }
    }
}
