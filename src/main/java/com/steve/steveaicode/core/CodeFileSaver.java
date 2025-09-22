package com.steve.steveaicode.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.steve.steveaicode.ai.model.HtmlCodeResult;
import com.steve.steveaicode.ai.model.MultiFileCodeResult;
import com.steve.steveaicode.model.enums.CodeGenTypeEnum;

import java.io.File;

@Deprecated
public class CodeFileSaver {

    // 文件保存的根目录
    private static final String FILE_SAVE_ROOT_DIR = System.getProperty("user.dir") + "/tmp/code_output";

    /**
     * 保存 HTML 代码文件
     *
     * @param htmlCodeResult HTML代码结果
     * @return 保存的文件
     */
    public static File saveHtmlCodeResult(HtmlCodeResult htmlCodeResult) {
        String filePath = buildFileUniquePath(CodeGenTypeEnum.HTML.getValue());
        writeToFile(filePath, "index.html", htmlCodeResult.getHtmlCode());
        return new File(filePath);
    }

    /**
     * 保存多文件代码文件
     *
     * @param multiFileCodeResult 多文件代码结果
     * @return 保存的文件
     */
    public static File saveMultiFileCodeResult(MultiFileCodeResult multiFileCodeResult) {
        String filePath = buildFileUniquePath(CodeGenTypeEnum.MULTI_FILE.getValue());
        writeToFile(filePath, "index.html", multiFileCodeResult.getHtmlCode());
        writeToFile(filePath, "style.css", multiFileCodeResult.getCssCode());
        writeToFile(filePath, "script.js", multiFileCodeResult.getJsCode());
        return new File(filePath);
    }

    /**
     * 构建文件保存路径
     *
     * @param bizType 代码生成类型
     * @return 文件保存路径
     */
    private static String buildFileUniquePath(String bizType) {
        String uniqueDirName = StrUtil.format("{}_{}", bizType, IdUtil.fastSimpleUUID());
        String uniquePath = FILE_SAVE_ROOT_DIR + File.separator + uniqueDirName;
        FileUtil.mkdir(uniquePath);
        return uniquePath;
    }

    /**
     * 保存文件
     *
     * @param filePath    文件路径
     * @param fileName    文件名
     * @param fileContent 文件内容
     */
    private static void writeToFile(String filePath, String fileName, String fileContent) {
        String fileFullPath = filePath + File.separator + fileName;
        FileUtil.writeUtf8String(fileContent, fileFullPath);
    }
}
