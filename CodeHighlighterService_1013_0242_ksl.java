// 代码生成时间: 2025-10-13 02:42:30
package com.codehighlighter;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
# TODO: 优化性能
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for highlighting code syntax.
 */
@Service
public class CodeHighlighterService {

    private static final String KEYWORD_PATTERN = "\\b(abstract|continue|for|new|switch|assert|default|goto|package|synchronized|boolean|do|if|private|this|break|double|implements|protected|throw|byte|else|import|public|throws|case|enum|instanceof|return|transient|catch|extends|int|short|try|char|final|interface|static|void|class|finally|long|strictfp|volatile|const|float|native|super|while)\\b";
    private static final String STRING_PATTERN = "\\"(\\\\.|[^\\