package com.msa.bookstore.application.service.bookinfo;

public record RegisterBookInfoCommand(String title, String author, Long isbn) {

}
