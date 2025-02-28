package org.make.ext.formatter;

import io.spring.javaformat.config.JavaFormatConfig;
import io.spring.javaformat.formatter.Edit;
import io.spring.javaformat.formatter.Formatter;
import io.spring.javaformat.formatter.StreamsFormatter;
import io.spring.javaformat.formatter.eclipse.EclipseCodeFormatter;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.IRegion;
import org.eclipse.text.edits.TextEdit;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.CompilationUnitVisitor;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TopLevelEnumeration;
import org.mybatis.generator.api.dom.java.render.TopLevelClassRenderer;
import org.mybatis.generator.api.dom.java.render.TopLevelEnumerationRenderer;
import org.mybatis.generator.api.dom.java.render.TopLevelInterfaceRenderer;
import org.mybatis.generator.config.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.Map;

import static io.spring.javaformat.config.IndentationStyle.SPACES;
import static io.spring.javaformat.config.JavaBaseline.V17;
import static io.vavr.API.Try;

/**
 * @see io.spring.javaformat.formatter.Formatter
 * @see CodeFormatter
 * @see org.eclipse.jdt.core.JavaCore
 */
public class ThorFormatter implements CompilationUnitVisitor<String>, JavaFormatter, EclipseCodeFormatter {

    private final Logger logger = LoggerFactory.getLogger(ThorFormatter.class);

    protected Context context;

    private final Formatter formatter;

    private final StreamsFormatter delegate;

    public ThorFormatter() {
        formatter = new Formatter(JavaFormatConfig.of(V17, SPACES));
        delegate = new StreamsFormatter(formatter);
    }

    @Override
    public String getFormattedContent(CompilationUnit compilationUnit) {
        return compilationUnit.accept(this);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String visit(TopLevelClass compilationUnit) {
        String source = new TopLevelClassRenderer().render(compilationUnit);
        return apply(source);
    }

    @Override
    public String visit(TopLevelEnumeration compilationUnit) {
        String source = new TopLevelEnumerationRenderer().render(compilationUnit);
        return apply(source);
    }

    @Override
    public String visit(Interface compilationUnit) {
        String source = new TopLevelInterfaceRenderer().render(compilationUnit);
        return apply(source);
    }

    @Override
    public TextEdit format(int kind, String source, int offset, int length, int indentationLevel, String lineSeparator) {
        return formatter.format(kind, source, offset, length, indentationLevel, lineSeparator);
    }

    @Override
    public TextEdit format(int kind, String source, IRegion[] regions, int indentationLevel, String lineSeparator) {
        return formatter.format(kind, source, regions, indentationLevel, lineSeparator);
    }

    @Override
    public String createIndentationString(int indentationLevel) {
        return formatter.createIndentationString(indentationLevel);
    }

    @Override
    public void setOptions(Map<String, String> options) {
        formatter.setOptions(options);
    }

    String apply(String source) {
        return Try(() -> delegate.format(new StringReader(source))).mapTry(Edit::getFormattedContent)
                .getOrElseGet(err -> {
                    logger.error(err.getMessage());
                    return source;
                });
    }
}
