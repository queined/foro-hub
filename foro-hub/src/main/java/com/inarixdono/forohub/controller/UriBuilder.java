package com.inarixdono.forohub.controller;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

public class UriBuilder {
    public static URI buildForId(UriComponentsBuilder builder, String path, String id) {
        return builder.path(path).buildAndExpand(id).toUri();
    }
}
