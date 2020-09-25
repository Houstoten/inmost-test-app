package com.inmost.bulletin_board.FileSystem.Exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileBrokenException extends RuntimeException {
    public FileBrokenException() {
        super("Whoops! Broken file :(");
        log.error("Whoops! Broken image :(");
    }

}
