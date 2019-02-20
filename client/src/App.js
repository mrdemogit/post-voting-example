import React, { Component } from 'react'
import PostList from './components/PostList'
import { Normalize } from 'styled-normalize'
import styled from 'styled-components'
import 'typeface-roboto'
import withData from './withData'

const StyledApp = styled.div`
  background: #fff;
  height: 100vh;
  padding: 2rem;
`

const PostListWithData = withData(PostList)

class App extends Component {
  render () {
    return (
      <StyledApp className='App'>
        <Normalize />
        <PostListWithData />
      </StyledApp>
    )
  }
}

export default App
