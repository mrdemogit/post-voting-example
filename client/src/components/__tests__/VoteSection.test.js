import React from 'react'
import ShallowRenderer from 'react-test-renderer/shallow'
import VoteSection from '../VoteSection'

describe('VoteSection', () => {
  it('should render correctly', () => {
    const renderer = new ShallowRenderer()
    renderer.render(
      <VoteSection
        uuid='uuid1'
        votes={123}
        upvote={jest.fn()}
        downvote={jest.fn()}
      />
    )

    expect(renderer.getRenderOutput()).toMatchSnapshot()
  })
})
