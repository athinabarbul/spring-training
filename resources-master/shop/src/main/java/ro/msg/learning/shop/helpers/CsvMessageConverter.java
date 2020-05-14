package ro.msg.learning.shop.helpers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class CsvMessageConverter<T> extends AbstractGenericHttpMessageConverter<List> {

	public CsvMessageConverter() {
		super(new MediaType("text", "csv"));
	}

	public static <T> List<T> fromCsv(Class<T> tClass, InputStream inputStream) throws IOException {
		CsvSchema stockLinesSchema = CsvSchema.emptySchema().withHeader();
		CsvMapper csvMapper = new CsvMapper();

		MappingIterator<T> stockLines = csvMapper.readerFor(tClass)
				.with(stockLinesSchema)
				.readValues(inputStream);

		return stockLines.readAll();
	}

	public void toCsv(Class<T> tClass, List<T> tList, OutputStream outputStream) throws IOException {

		CsvMapper csvMapper = new CsvMapper();
		CsvSchema csvSchema = csvMapper.schemaFor(tClass).withHeader().withColumnReordering(false);

		csvMapper.writerFor(tClass).with(csvSchema).writeValuesAsArray(outputStream).writeAll(tList);
		outputStream.close();

	}

	@SneakyThrows
	@Override
	public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
		return mediaType != null && mediaType.getType().equalsIgnoreCase("text") && mediaType.getSubtype().equalsIgnoreCase("csv")
				&& Class.forName(((ParameterizedType) type).getRawType().getTypeName()).equals(List.class);
	}

	@SneakyThrows
	@Override
	public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
		return mediaType != null && mediaType.getType().equalsIgnoreCase("text") && mediaType.getSubtype().equalsIgnoreCase("csv")
				&& Class.forName(((ParameterizedType) type).getRawType().getTypeName()).equals(List.class);
	}

	@Override
	protected void writeInternal(List list, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
		ParameterizedType pType = (ParameterizedType) type;
		Class<T> clazz = (Class<T>) pType.getActualTypeArguments()[0];
		toCsv(clazz, list, httpOutputMessage.getBody());
	}

	@Override
	protected List readInternal(Class<? extends List> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		return null;
	}

	@Override
	public List read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		ParameterizedType pType = (ParameterizedType) type;
		Class<T> clazz = (Class<T>) pType.getActualTypeArguments()[0];
		return fromCsv(clazz, httpInputMessage.getBody());
	}


}
