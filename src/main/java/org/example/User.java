package org.example;

import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class User {
    private String name; // Kullanıcının ismini saklayan değişken
    private LinkedHashMap<Integer, Post> posts; // Kullanıcının gönderilerini saklayan değişken
    private HashSet<User> following; // Kullanıcının takip ettiği kullanıcılar
    private TreeSet<Post> favorites; // Kullanıcının favorilerine eklediği gönderiler
    private static int postCounter = 0; // Gönderi sayaç değişkeni - Kaç gönderi olduğu

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
        System.out.println(name + ", " + user.getName() + " kullanıcısını takip ediyor");
    }

    public void createPost(String content) {
        Post newPost = new Post(postCounter++, this, content);
        posts.put(newPost.getId(), newPost);
        System.out.println(name + " yeni bir gönderi yayınladı: " + content);
    }

    public void addCommentToPost(User user, int postId, String comment) {
        Post post = user.getPost(postId);
        if (post != null) {
            post.addComment(new Comment(this, comment));
            System.out.println(name + ", " + user.getName() + "'in gönderisine yorum yaptı: " + comment);
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
        System.out.println("\n" + name + " 'in Ana Sayfası");
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
