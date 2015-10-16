package URLShortener.util;

import spark.Request;
import spark.Response;
import spark.ResponseTransformer;

/**
 *
 */
public class JsonTransformer implements ResponseTransformer {

    /**
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public String render(final Object model) throws Exception {
        return JacksonUtil.newObjectMapper().writeValueAsString(model);
    }
}
