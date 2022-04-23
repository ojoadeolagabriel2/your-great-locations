local namespace = std.extVar('APP_NAMESPACE');

local data = [
                 {
                     apiVersion: 1,
                     kind: "Namespace",
                     metadata: {
                         name: namespace,
                         namespace: namespace,
                     }
                 },
                 {
                     apiVersion: 1,
                     kind: "Secret",
                     metadata: {
                         creationTimestamp: "2022-04-19T04:08:17Z",
                         name: "regcred",
                         namespace: namespace,
                         resourceVersion: 6151391,
                         uid: "1c605849-ce31-4c0f-b054-ab65dd0a8990",
                     },
                     type: "kubernetes.io/dockerconfigjson",
                     data: {
                       ['.dockerconfigjson']: "eyJhdXRocyI6eyJyZWdpc3RyeS0xLmRvY2tlci5pbyI6eyJ1c2VybmFtZSI6Im9qb2FkZW9sYWdhYnJpZWwiLCJwYXNzd29yZCI6IlNhdHVybjQzMiQiLCJlbWFpbCI6Im9qb2FkZW9sYWdhYnJpZWxAZ21haWwuY29tIiwiYXV0aCI6ImIycHZZV1JsYjJ4aFoyRmljbWxsYkRwVFlYUjFjbTQwTXpJayJ9fX0",
                     },
                 },
                 {
                     apiVersion: 1,
                     kind: "ConfigMap",
                     metadata: {
                         name: "agent-service-config-map",
                         namespace: namespace,
                     }
                 },
 ];

std.manifestYamlStream(
    data
    ,quote_keys=false
    ,indent_array_in_object=false
    ,c_document_end = false
)