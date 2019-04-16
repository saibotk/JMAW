package de.saibotk.jmaw;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@linkplain Converter.Factory converter} for a list of strings from a {@code text/plain} body.
 *
 * @author saibotk
 * @since 1.0
 */
final class StringLineListConverterFactory extends Converter.Factory {

    public static StringLineListConverterFactory create() {
        return new StringLineListConverterFactory();
    }

    private StringLineListConverterFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(type) != List.class && getParameterUpperBound(0, (ParameterizedType) type) != String.class) {
            return null;
        }

        return StringLineListConverter.INSTANCE;
    }

    /**
     * This is a custom converter for retrofit, to be able to deserialize a text/plain response into a string list.
     *
     * @author saibotk
     * @since 1.0
     */
    static final class StringLineListConverter implements Converter<ResponseBody, List<String>> {
        static final StringLineListConverter INSTANCE = new StringLineListConverter();

        @Override
        public List<String> convert(ResponseBody value) throws IOException {
            try {
                BufferedReader reader = new BufferedReader(value.charStream());
                List<String> list = new ArrayList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }

                return list;
            } finally {
                value.close();
            }
        }
    }

}