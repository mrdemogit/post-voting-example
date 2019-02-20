import React from 'react'
import ShallowRenderer from 'react-test-renderer/shallow'
import { Comment } from '../Comment'

describe('Comment', () => {
  it('should render correctly', () => {
    const renderer = new ShallowRenderer()
    renderer.render(
      <Comment
        uuid='uuid1'
        author='authorTest'
        content='contentTest'
        votes={123}
        upvote={jest.fn()}
        downvote={jest.fn()}
      />
    )

    expect(renderer.getRenderOutput()).toMatchSnapshot()
  })
})
