import React from 'react'
import PropTypes from 'prop-types'
import styled from 'styled-components'
import Post from './Post'
import { PostType } from '../types'

const StyledList = styled.div`
  padding: 1em;
  font-family: 'Roboto';
  font-size: 0.9em;
`

const PostList = ({ posts, upvote, downvote, error }) => {
  return (
    <StyledList>
      {error}
      {posts.map((post) => (
        <Post key={post.uuid} {...post} upvote={upvote} downvote={downvote} />
      ))}
    </StyledList>
  )
}

PostList.propTypes = {
  posts: PropTypes.arrayOf(PropTypes.shape(PostType)),
  upvote: PropTypes.func.isRequired,
  downvote: PropTypes.func.isRequired,
  error: PropTypes.string
}

export default PostList
