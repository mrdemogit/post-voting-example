import React, { memo } from 'react'
import PropTypes from 'prop-types'
import styled from 'styled-components'
import { equals } from 'ramda'
import Comment from './Comment'
import VoteSection from './VoteSection'
import { PostType } from '../types'

const StyledPost = styled.div`
  padding: 0.8em;
  margin: 0.3em;
  background: #0075a1d4;
  border-radius: 1em;
  color: #fff;
`

const StyledName = styled.div`
  color: #000;
  font-style: italic;
`

export const Post = ({
  uuid,
  author,
  content,
  votes,
  comments,
  upvote,
  downvote
}) => (
  <StyledPost>
    <StyledName>{author}</StyledName>
    <div>{content}</div>
    <VoteSection
      uuid={uuid}
      upvote={upvote}
      downvote={downvote}
      votes={votes}
    />
    {comments.map((comment) => (
      <Comment
        key={comment.uuid}
        {...comment}
        upvote={upvote}
        downvote={downvote}
      />
    ))}
  </StyledPost>
)

Post.propTypes = {
  ...PostType,
  upvote: PropTypes.func.isRequired,
  downvote: PropTypes.func.isRequired
}

const shouldUpdate = (prev, next) => {
  return equals(prev, next)
}

export default memo(Post, shouldUpdate)
