package org.example;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class SocialMediaPlatform {
    public static void main(String[] args) {

    }

    class User {
        private String name;
        private LinkedHashMap<Integer, Post> posts;
        private HashSet<User> following;
        private TreeSet<Post> favorites;
        private static int postCounter = 0;

        public User(String name) {
            this.name = name;
            this.posts = new LinkedHashMap<>();
            this.following = new HashSet<>();
            this.favorites = new TreeSet<>();
        }

        public String getName() {
            return name;
        }

        public Post getPost(int postId) {
            return posts.get(postId);
        }

        public void follow(User user) {
            following.add(user);
            System.out.println(name + ", " + user.getName() + " kullanıcısını takip etme");
        }

        public void createPost(String content) {
            Post newPost = new Post(postCounter++, this, content);
            posts.put(newPost.getId(), newPost);
            System.out.println(name + " yeni bir gönderi yayına alma: " + content);
        }

        public void addCommentToPost(User user, int postId, String comment) {
            Post post = user.getPost(postId);
            if (post != null) {
                post.addComment(new Comment(this, comment));
                System.out.println(name + ", " + user.getName() + "'in gönderisine yorum yaptıı: " + comment);
            }
        }

        public void addToPostFavorites(User user, int postId) {
            Post post = user.getPost(postId);
            if (post != null) {
                favorites.add(post);
                System.out.println(name + ", " + user.getName() + "'in gönderisini beğendi: " + post.getContent());
            }
        }

        public void showFeed() {
            System.out.println("\n" + name + "'in Ana Sayfası");
            for (User user : following) {
                user.showPosts();
            }
        }

        public void showPosts() {
            for (Post post : posts.values()) {
                System.out.println(post.getContent());
                post.showComments();
            }
        }
    }

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

    class Comment {
        private User user;
        private String content;

        public Comment(User user, String content) {
            this.user = user;
            this.content = content;
        }

        public User getUser() {
            return user;
        }

        public String getContent() {
            return content;
        }
    }
}
