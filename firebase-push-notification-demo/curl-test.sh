#!/bin/bash
curl -X POST -H "Authorization: key=AAAA1Y9hWzY:APA91bH07YvlHuziVRhAy9AORz4nQ0BUdJU_gIpT_wwODYOS49sDxzscoJ6tJAvcrbrBKG3MPkYQtkgha6dkCpTPSACvPwb5uix02CrCPW62XbPZGRwK7MW1rbFtAhDcDGUS20jxd1li" -H "Content-Type: application/json" \
   -d '{
  "notification": {
        "title": "FCM Message",
        "body": "This is an FCM Message",
        "icon": "/itwonders-web-logo.png",
    }
  ,
  "to":"dRIYg7UyTYi4kyOwErNECy:APA91bFOSZ1dkNQ54u6waWx7I-XLQgL-PdEnNetMvxs_22tfLw2p527iEpEEbUjfNapwwTV1Qv6pni_B5L2QFusKTr-DIPpHaEr7UgI7WLbOE4Kr6WMjuyfQjjIuqEuZBiEwXfTduYrK"
}' https://fcm.googleapis.com/fcm/send
