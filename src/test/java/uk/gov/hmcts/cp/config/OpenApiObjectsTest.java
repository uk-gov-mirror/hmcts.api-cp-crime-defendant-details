package uk.gov.hmcts.cp.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import uk.gov.hmcts.cp.openapi.api.DefendantsApi;
import uk.gov.hmcts.cp.openapi.model.DefendantDetails;
import uk.gov.hmcts.cp.openapi.model.ErrorResponse;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class OpenApiObjectsTest {
    @Test
    void generated_error_response_should_have_expected_fields() {
        assertThat(ErrorResponse.class).hasDeclaredMethods("error", "message", "details", "traceId");
    }

    @Test
    void generated_defendant_details_should_have_expected_fields() {
        assertThat(DefendantDetails.class).hasDeclaredFields("defendantId", "masterDefendantId", "name", "dateOfBirth");
    }

    @Test
    void generated_defendants_api_should_have_expected_methods() {
        assertThat(DefendantsApi.class).hasDeclaredMethods("getDefendantsByCase");
    }
    @Test
    void generated_error_response_timestamp_should_be_instant() throws Exception {
        Field timestampField = ErrorResponse.class.getDeclaredField("timestamp");

        assertThat(timestampField.getType())
                .as("timestamp field type")
                .isEqualTo(Instant.class);
    }

    @Test
    void defendant_details_should_omit_date_of_birth_when_null() {
        DefendantDetails defendantDetails = DefendantDetails.builder()
                .defendantId(UUID.randomUUID())
                .name("Jane Doe")
                .dateOfBirth(null)
                .build();

        String json = new ObjectMapper().writeValueAsString(defendantDetails);

        assertThat(json).doesNotContain("dateOfBirth");
    }

    @Test
    void defendant_details_should_include_date_of_birth_when_present() {
        DefendantDetails defendantDetails = DefendantDetails.builder()
                .defendantId(UUID.randomUUID())
                .name("John Doe")
                .dateOfBirth(LocalDate.of(1980, 1, 31))
                .build();

        String json = new ObjectMapper().writeValueAsString(defendantDetails);

        assertThat(json).contains("\"dateOfBirth\":\"1980-01-31\"");
    }
}