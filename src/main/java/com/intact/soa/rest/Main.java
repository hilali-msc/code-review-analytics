package com.intact.soa.rest;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.WinHttpClients;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class Main {

	private static final String GITHUB_GRAPHQL_URL = "https://api.github.com/graphql";
	private static final String GITHUB_MY_TOKEN = "0a5cb3ada3ee46353925eca50f7717c3f4823b24";

	private Main() {
		if (!WinHttpClients.isWinAuthAvailable()) {
			throw new IllegalStateException("Integrated Win auth is not supported!!!");
		}
	}

	public static void main(String[] args) throws Exception {
		String schema = "type Query{hello: String}";
		
		sendQuery("{\"query\": \"query { viewer { login }}\"}");
		
//		sendQuery("{\"query\": \"query { repository(owner:\"iluwatar\", name:\"java-design-patterns\") { issues(last:20, states:CLOSED) { edges { node { title url labels(first:5) { edges { node { name } } } } } } } } \"}");
		
		sendQuery("{\"query\": \"query { repository(owner:'iluwatar', name:'java-design-patterns'){ commits(last:20) { edges { node { title url labels(first:5) { edges { node { name } } } } } } } } \"}");
		
		sendQuery("{\"query\": \"query { repository(owner:\"iluwatar\", name:\"java-design-patterns\"){ref(qualifiedName: \"master\") { target { ... on Commit { history(first: 10) { pageInfo { hasNextPage endCursor } edges { node { oid messageHeadline } } } } } }} } \"}");
		
	}		
		
		private static void test() throws Exception
		{

		URL url = new URL("https://api.github.com/graphql");

		Reader reader = new InputStreamReader(url.openStream());

		SchemaParser schemaParser = new SchemaParser();
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(reader);

		RuntimeWiring runtimeWiring = newRuntimeWiring()
				.type("Query", builder -> builder.dataFetcher("repository", new StaticDataFetcher("world"))).build();

		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

		GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
		ExecutionResult executionResult = build.execute("{hello}");

		System.out.println(executionResult.getData().toString());
		// Prints: {hello=world}
	}

	private static void sendQuery(String query) throws Exception {
		CloseableHttpClient httpclient = WinHttpClients.createDefault();
		CloseableHttpResponse response = null;

		HttpPost httpPost = new HttpPost(GITHUB_GRAPHQL_URL);

		httpPost.addHeader("Authorization", "Bearer "+GITHUB_MY_TOKEN);
		httpPost.addHeader("Accept", "application/json");
		
		StringEntity entity = new StringEntity(query);

		httpPost.setEntity(entity);
		response = httpclient.execute(httpPost);
		
		print(response);
	}
	
	private static void print(CloseableHttpResponse response)
	{
		 try{
	            BufferedReader reader= new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            String line= null;
	            StringBuilder builder= new StringBuilder();
	            while((line=reader.readLine())!= null){

	                builder.append(line);

	            }
	            System.out.println(builder.toString());
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }		
	}
}
