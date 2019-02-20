import React, { Fragment } from 'react'
import PropTypes from 'prop-types'
import styled from 'styled-components'
import UpvoteIcon from './ArrowIcon'

const StyledButton = styled.button`
  font-size: 1em;
  margin: 0.3em;
  padding: 0.1em 1em;
  border: 2px solid palevioletred;
  border-radius: 3px;
  color: #338cb1;
  border-color: #fff;
  cursor: pointer;
`

const StyledButtonUp = styled(StyledButton)`
  transform: rotate(180deg);
  transform-origin: 50% 50%;
`

const VoteSection = ({ uuid, votes, upvote, downvote }) => {
  return (
    <Fragment>
      <StyledButtonUp onClick={() => upvote(uuid)}>
        <UpvoteIcon />
      </StyledButtonUp>
      <StyledButton onClick={() => downvote(uuid)}>
        <UpvoteIcon />
      </StyledButton>
      <span>{votes}</span>
    </Fragment>
  )
}

VoteSection.propTypes = {
  uuid: PropTypes.string.isRequired,
  votes: PropTypes.number.isRequired,
  upvote: PropTypes.func.isRequired,
  downvote: PropTypes.func.isRequired
}

export default VoteSection
