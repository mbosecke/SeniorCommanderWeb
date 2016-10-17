package com.mitchellbosecke.seniorcommander.web.twitch;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by mitch_000 on 2016-09-24.
 */
public class TwitchApi {

    private static final int VERSION = 3;
    private static final String BASE_URL = "https://api.twitch.tv/kraken";
    private static final ObjectMapper mapper = new ObjectMapper();
    private final String clientId;

    public TwitchApi(String clientId) {
        this.clientId = clientId;
    }

    public ChannelFollowsPage followers(String channel) {
        return followers(channel, null);
    }

    public ChannelFollowsPage followers(String channel, String cursor) {
        HttpClient client = buildClient();
        client.pathSegment("channels").pathSegment(cleanChannelName(channel)).pathSegment("follows")
                .param("limit", "100");
        if (cursor != null) {
            client.param("cursor", cursor);
        }

        ChannelFollowsPage page = parse(client.get(), ChannelFollowsPage.class);
        page.setChannel(channel);
        return page;
    }

    public Channel channel(String channel){
        return parse(buildClient().pathSegment("channels").pathSegment(cleanChannelName(channel)).get(), Channel.class);
    }

    public Stream stream(String channel) {
        StreamContainer container = parse(buildClient().pathSegment("streams").pathSegment(cleanChannelName(channel))
                .get(), StreamContainer.class);
        return container.getStream();
    }

    private <T> T parse(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String cleanChannelName(String channel) {
        return channel.replaceAll("#", "");
    }

    private HttpClient buildClient() {
        HttpClient client = new HttpClient(BASE_URL);
        client.header("ACCEPT", "application/vnd.twitchtv.v" + Integer.toString(VERSION) + "+json");
        client.header("Client-ID", clientId);
        return client;
    }

}
