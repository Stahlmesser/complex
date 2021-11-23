package com.complex.utils;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.quicksight.AmazonQuickSight;
import com.amazonaws.services.quicksight.AmazonQuickSightClientBuilder;
import com.amazonaws.services.quicksight.model.GenerateEmbedUrlForRegisteredUserRequest;
import com.amazonaws.services.quicksight.model.GenerateEmbedUrlForRegisteredUserResult;
import com.amazonaws.services.quicksight.model.RegisteredUserEmbeddingExperienceConfiguration;
import com.amazonaws.services.quicksight.model.RegisteredUserQuickSightConsoleEmbeddingConfiguration;

/**
 * Class to call QuickSight AWS SDK to get url for QuickSight console embedding.
 */
public class Embedding {

    private final AmazonQuickSight quickSightClient;

    public Embedding() {
        this.quickSightClient = AmazonQuickSightClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1.getName())
                .withCredentials(new AWSCredentialsProvider() {
                                     @Override
                                     public AWSCredentials getCredentials() {
                                         // provide actual IAM access key and secret key here
                                         return new BasicAWSCredentials("access-key", "secret-key");
                                     }

                                     @Override
                                     public void refresh() {}
                                 }
                )
                .build();
    }


    public String getQuicksightEmbedUrl(
            final String accountId ,
            final String userArn, // Registered user arn to use for embedding.
            // Refer to Get Embed Url section in developer portal to find out how to get user arn for a QuickSight user.
            final String initialPath
    ) throws Exception {
        final RegisteredUserEmbeddingExperienceConfiguration experienceConfiguration = new RegisteredUserEmbeddingExperienceConfiguration()
                .withQuickSightConsole(new RegisteredUserQuickSightConsoleEmbeddingConfiguration().withInitialPath(initialPath));
        final GenerateEmbedUrlForRegisteredUserRequest generateEmbedUrlForRegisteredUserRequest = new GenerateEmbedUrlForRegisteredUserRequest();
        generateEmbedUrlForRegisteredUserRequest.setAwsAccountId(accountId);
        generateEmbedUrlForRegisteredUserRequest.setUserArn(userArn);
        generateEmbedUrlForRegisteredUserRequest.setExperienceConfiguration(experienceConfiguration);

        final GenerateEmbedUrlForRegisteredUserResult generateEmbedUrlForRegisteredUserResult = quickSightClient.generateEmbedUrlForRegisteredUser(generateEmbedUrlForRegisteredUserRequest);

        return generateEmbedUrlForRegisteredUserResult.getEmbedUrl();
    }
}
