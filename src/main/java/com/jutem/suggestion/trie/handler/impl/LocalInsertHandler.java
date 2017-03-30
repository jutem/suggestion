package com.jutem.suggestion.trie.handler.impl;

import com.jutem.suggestion.exceptionn.SuggestionException;
import com.jutem.suggestion.trie.core.TrieTree;
import com.jutem.suggestion.trie.handler.InsertWordHandler;
import com.jutem.suggestion.trie.persist.TriePersist;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 本地测试用
 */
public class LocalInsertHandler implements InsertWordHandler {

    private TriePersist triePersist;
    private TrieTree trieTree;

    private volatile boolean needSave = false;

    public LocalInsertHandler(TriePersist triePersist, TrieTree trieTree) {
        if(triePersist == null || trieTree == null)
            throw new SuggestionException("localInsertHandler 初始化错误");
        this.triePersist = triePersist;
        this.trieTree = trieTree;
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(needSave) {
                    triePersist.saveTree(trieTree);
                    System.out.println("save tree complete");
                    needSave = false;
                }
            }
        }, 10,5, TimeUnit.SECONDS);
    }

    @Override
    public void insertWord(String word) {
        trieTree.insert(word);
        needSave = true;
    }
}
