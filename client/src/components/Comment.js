import React, { memo } from 'react'
import PropTypes from 'prop-types'
import styled from 'styled-components'
import VoteSection from './VoteSection'
import { CommentType } from '../types'

const StyledComment = styled.div`
  padding: 0.5em 1em;
  margin: 0.2em;
  background: #04597ad4;
  border-radius: 0.3em;
  color: #fff;
`

const StyledName = styled.div`
  color: #000;
  font-style: italic;
`

export const Comment = ({ uuid, author, content, votes, upvote, downvote }) => (
  <StyledComment>
    <StyledName>{author}</StyledName>
    <div>{content}</div>
    <VoteSection
      uuid={uuid}
      votes={votes}
      upvote={upvote}
      downvote={downvote}
    />
  </StyledComment>
)

Comment.propTypes = {
  ...CommentType,
  upvote: PropTypes.func.isRequired,
  downvote: PropTypes.func.isRequired
}

export default memo(Comment)
