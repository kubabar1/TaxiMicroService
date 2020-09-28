## Example use:

To run full example Apache Kafka needs to be configured.

After run backend by **gradle bootRun**:
1. `yarn install`
2. `yarn global add http-server`
3. `yarn run build`
4. `http-server ./dist --cors -o -p 8081`
5. click *Connect* button - notification should display
5. Type in new console window: `kafka-console-producer.sh -broker-list localhost:9092 --topic send_user_notification_topic`
6. Paste this example json `{"type":"INFO","content": "Test message","sender": "message-service","receiverUsername": "adam123"}`
7. Notification containing above json should appear in browser.