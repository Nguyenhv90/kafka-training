import './App.css';
import React from 'react';
import { useState } from 'react';
import { randomColor } from './utils/common';
import SockJsClient from 'react-stomp';
import chatAPI from './services/chatAPI';
import Messages from './components/Messages';
import Input from './components/Input';
import LoginForm from './components/LoginForm';

const SOCKET_URL = 'http://localhost:8081/ws-chat/'
const App = () => {
  const [messages, setMessages] = useState([]);
  const [user, setUser] = useState(null);

  let onConnected = () => {
    console.log("Connected!!")
  }

  let onMessageReceived = (msg) => {
    console.log('New Message Received!!', msg);
    setMessages(messages.concat(msg));
  }

  let onSendMessage = (msgText) => {
    chatAPI.sendMessage(user.username, msgText).then(res => {
      console.log('Sent', res);
    }).catch(err => {
      console.log('Error Occured while sending message to api');
    })
  }

  let handleLoginSubmit = (username) => {
    console.log(username, " Logged in..");
    setUser({
      username: username,
      color: randomColor()
    })

  }

  return (
    <div className="App">
      {!!user ?
        (
          <>
            <SockJsClient
              url={SOCKET_URL}
              topics={['/topic/group']}
              onConnect={onConnected}
              onDisconnect={console.log("Disconnected!")}
              onMessage={msg => onMessageReceived(msg)}
              debug={false}
            />
            <Messages
              messages={messages}
              currentUser={user}
            />
            <Input onSendMessage={onSendMessage} />
          </>
        ) :
        <LoginForm onSubmit={handleLoginSubmit} />
      }
    </div>
  )
}
export default App;
