package com.steve.steveaicode.core.filesaver;

import cn.hutool.core.util.StrUtil;
import com.steve.steveaicode.ai.model.HtmlCodeResult;
import com.steve.steveaicode.exception.BusinessException;
import com.steve.steveaicode.exception.ErrorCode;
import com.steve.steveaicode.model.enums.CodeGenTypeEnum;

/**
 *  HTML 代码文件保存器
 */
public class HtmlCodeFileSaverTemplate extends  CodeFileSaverTemplate<HtmlCodeResult>{

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return  CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码结果对象不能为空
        if (StrUtil.isEmpty(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML 代码不能为空");
        }

    }
}
