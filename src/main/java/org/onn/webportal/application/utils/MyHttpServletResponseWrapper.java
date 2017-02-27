package org.onn.webportal.application.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyHttpServletResponseWrapper extends HttpServletResponseWrapper {

	private ServletOutputStream outputStream;
	private PrintWriter writer;
	private ServletOutputStreamCopier copier;

	public MyHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (writer != null) {
			throw new IllegalStateException("getWriter() has already been called on this response.");
		}

		if (outputStream == null) {
			outputStream = getResponse().getOutputStream();
			copier = new ServletOutputStreamCopier(outputStream);
		}

		return copier;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (outputStream != null) {
			throw new IllegalStateException("getOutputStream() has already been called on this response.");
		}

		if (writer == null) {
			copier = new ServletOutputStreamCopier(getResponse().getOutputStream());
			writer = new PrintWriter(new OutputStreamWriter(copier, getResponse().getCharacterEncoding()), true);
		}

		return writer;
	}
}
