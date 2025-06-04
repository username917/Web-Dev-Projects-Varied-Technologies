// import { useState } from 'react'

import './App.css'
import {Routes, Route, BrowserRouter } from "react-router-dom";
import Login from "./components/Security/Login.jsx";
import Administration from "./components/Administration/Administration.jsx";

function App() {
  

  return (
    
	<BrowserRouter>
	
		<div>
		  	<h1>Some routes here</h1>
			<Routes>
				<Route path="/project/login" element={<Login />}/>
				<Route path="/administration" element={<Administration />}/>
			</Routes>	
	      </div>
	    
	
	
	
	
	
	</BrowserRouter>
      
  )
}

export default App
