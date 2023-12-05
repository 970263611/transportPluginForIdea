package com.dahuaboke.transport.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

/**
 * author: dahua
 * date: 2023/12/5 18:21
 */
public class TransportAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor data = e.getData(CommonDataKeys.EDITOR);
        e.accept();
    }
}
