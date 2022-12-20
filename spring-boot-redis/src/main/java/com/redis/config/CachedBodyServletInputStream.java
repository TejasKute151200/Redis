package com.redis.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CachedBodyServletInputStream extends ServletInputStream {
	
	private static final Logger LOG = LoggerFactory.getLogger(CachedBodyServletInputStream.class);
	private InputStream cachedBodyInputStream;
	
	public CachedBodyServletInputStream(byte[] cachedBody) {
		LOG.info("CONFIGURE >> Constructor Loaded >> CachedBodyServletInputStream() >>");
		this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
	}

	@Override
	public boolean isFinished() {
		try {
			LOG.info("CONFIGURE >> Cached is Finished >> ");
			return cachedBodyInputStream.available() == 0;
		} catch (IOException e) {
			LOG.info("CONFIGURE >> Cached is finished Exception >> ");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isReady() {
		LOG.info("CONFIGURE >> Is Read");
		return true;
	}

	@Override
	public void setReadListener(ReadListener readListener) {
		LOG.info("CONFIGURE >> Read Listener");
		throw new UnsupportedOperationException();
	}

	@Override
	public int read() throws IOException {
		LOG.info("CONFIGURE >> Read");
		return cachedBodyInputStream.read();
	}
}